import { ScrollArea } from "@/components/ui/scroll-area";
import { useParams } from "react-router-dom"

const IssueDetails = () => {
    const { projectId, issueId } = useParams(); 
  return (
    <div className="px-20 py-8 text-gray-400">
      <div className="flex justify-between border p-10 rounded-lg">
        <ScrollArea className="h-[80vh] w-[60%]">
          <div>
            <h1 className="text-lg font-semibold text-gray-400"> Create Navbar</h1>

            <div className="py-5">
              <h2 className="font-semibold">Description</h2>
              <p className="text-gray-400 text-sm mt-3">Lorem, ipsum.</p>
            </div>

            <div className="mt-5">
                <h1 className="pb-3">Activity</h1>
            </div>
          </div>
        </ScrollArea>

      </div>

    </div>
  ) 
}

export default IssueDetails